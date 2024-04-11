import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { SetSearchService } from '../services/set-search.service';
import { CardSearchService } from '../services/card-search.service';
import { Card, CardSet } from '../models/base-model';
import { Observable, defer, expand, map, mergeMap, of, take } from 'rxjs';
import { ButtonsModule } from 'ngx-bootstrap/buttons'

@Component({
  selector: 'app-search-box',
  standalone: true,
  imports: [
    FormsModule,
    ButtonsModule
  ],
  templateUrl: './search-box.component.html',
  styleUrl: './search-box.component.scss'
})
export class SearchBoxComponent {

  search_text: string = '';
  locked_prefix: string = '';
  locked_set?:CardSet;
  is_locked = false;

  card:Card = new Card();

  private min_set_size = 3;
  private max_set_size = 5;

  constructor(
    private set_search:SetSearchService,
    private card_search:CardSearchService
  ) {}

  toggleLockComponent(): void {
    if(this.search_text.length < 3 && !this.is_locked) {
      return;
    }

    this.is_locked = !this.is_locked;

    // If the state is locked, it'll search a set and lock it
    if(this.is_locked) {
      this.searchSetForLock().subscribe(res => {
        if(!res) {
          return;
        }
          this.locked_prefix = res.code || '-';
          this.search_text = this.search_text.substring(res.code?.length || 0);
      });
      return;
    }

    // Reset values to default input otherwise
    this.search_text = this.locked_prefix + this.search_text;
    this.locked_prefix = '';
  }

  /**
   * Recursive method that will take input prefix and try to match it with the service's result
   * @param set$ Observable that handles the set responses - starts with the first search
   * @param word_len Dynamic length that increases to a five MAX.
   * @returns Emmited observable with result or nothing if nothing matches
   */
  private searchSetForLock(
      set$: Observable<CardSet> = this.set_search.fetchSet(this.search_text.substring(0, this.min_set_size)), 
      word_len: number = this.min_set_size
  ): Observable<CardSet> {
    return defer(() => {
      // let word_len = this.min_set_size;
      // let service_set$ = this.set_search.fetchSet(this.search_text.substring(0, word_len));

      return set$.pipe(
        expand((set_result) => {
          if(set_result || word_len > this.max_set_size) {
            return of(set_result);
          }
          word_len++;

          return this.searchSetForLock(this.set_search.fetchSet(this.search_text.substring(0, word_len)), word_len);
        }),
        take(word_len),
        map((result) => {
          if(!result) {
            console.error('NO SET FOUND!');
          }
          return result;
        })
      );
    });
  }

  searchCard():void {
    if(!this.search_text) {
      return;
    }

    let card_search_result$ = this.is_locked ? 
      this.card_search.fetchCard(this.locked_prefix, this.search_text) : 
      this.card_search.fetchCardByCode(this.search_text);

    card_search_result$.subscribe((result) => {
      this.card = result;
      console.log(result);
    });
  }
}
