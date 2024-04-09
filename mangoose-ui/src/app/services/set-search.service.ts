import { Injectable } from '@angular/core';
import { ENV } from '../environment';
import { CardSet } from '../models/base-model';
import { HttpClient } from '@angular/common/http';
import { Observable, map, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SetSearchService {

  private baseURL = `${ENV.SERVICE_ROOT}/set-search`;

  sets:Map<string, CardSet> = new Map();

  constructor(private http: HttpClient) { }

  public fetchSet(code:string):Observable<CardSet> {
    const set = this.sets.get(code);
    if(!set) {
      const url = `${this.baseURL}/${code}`
      return this.http.get<CardSet>(url)
      .pipe(
        map((response) => {
          this.sets.set(response.code || '', response);
          return response;
        })
      );
    }
    return of(set);
  }

  public all():Observable<any> {
    const count$ = this.http.get<number>(`${this.baseURL}/count`);
    const fetchAll$ = this.http.get<CardSet[]>(`${this.baseURL}`);
    
    const sets$ = count$.pipe(
      map( (cntRes):Observable<Map<string, CardSet>> => {
        if(cntRes > this.sets.size){
          return fetchAll$.pipe(
            map((setData):Map<string, CardSet> => {
              for(let sd of setData) {
                this.sets.set(sd.code || '', sd);
              }
              return this.sets;
            })
          );
        }
        else {
          return of(this.sets);
        }
      })
    );

    return sets$;
  }
}
