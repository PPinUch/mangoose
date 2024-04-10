import { Injectable } from '@angular/core';
import { Card } from '../models/base-model';
import { Observable, map, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { ENV } from '../environment';

@Injectable({
  providedIn: 'root'
})
export class CardSearchService {

  private base_card_url = `${ENV.SERVICE_ROOT}/card-search`;
  private base_code_url = `${ENV.SERVICE_ROOT}/code-number`;

  private cards_proxy:Map<string, Card> = new Map();

  constructor(private http: HttpClient) { }

  fetchCard(set:string, col_num:number):Observable<Card> {
    const card_code = set+col_num;
    const card = this.cards_proxy.get(card_code);
    if(!card) {
      const url = `${this.base_card_url}/${set}/${col_num}`;
      return this.http.get<Card>(url).pipe(
        map((resp) => {
          if(resp) {
            this.cards_proxy.set(card_code, resp);
          }
          return resp;
        })
      );
    }
    return of(card);
  }

  fetchCardByCode(card_code:string):Observable<Card> {
    const card = this.cards_proxy.get(card_code);
    if(!card) {
      const url = `${this.base_code_url}/${card_code}`;
      return this.http.get<Card>(url).pipe(
        map((resp) => {
          if(resp) {
            this.cards_proxy.set(card_code, resp);
          }
          return resp;
        })
      );
    }
    return of(card);
  }

}
