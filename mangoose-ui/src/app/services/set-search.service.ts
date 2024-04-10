import { Injectable } from '@angular/core';
import { ENV } from '../environment';
import { CardSet } from '../models/base-model';
import { HttpClient } from '@angular/common/http';
import { Observable, map, mergeMap, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SetSearchService {

  private base_url = `${ENV.SERVICE_ROOT}/set-search`;

  private sets_proxy:Map<string, CardSet> = new Map();

  constructor(private http: HttpClient) { }

  public fetchSet(code:string):Observable<CardSet> {
    const set = this.sets_proxy.get(code);
    if(!set) {
      const url = `${this.base_url}/${code}`
      return this.http.get<CardSet>(url)
      .pipe(
        map((response) => {
          this.sets_proxy.set(response.code || '', response);
          return response;
        })
      );
    }
    return of(set);
  }

  public all():Observable<Map<string, CardSet>> {
    const count$ = this.http.get<number>(`${this.base_url}/count`);
    const fetchAll$ = this.http.get<CardSet[]>(`${this.base_url}`);
    
    const sets$ = count$.pipe(
      mergeMap( (cntRes) => {
        if(cntRes > this.sets_proxy.size){
          return fetchAll$.pipe(
            map((setData):Map<string, CardSet> => {
              for(let sd of setData) {
                this.sets_proxy.set(sd.code || '', sd);
              }
              return this.sets_proxy;
            })
          );
        }
        else {
          return of(this.sets_proxy);
        }
      })
    );

    return sets$;
  }
}
