import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { SetSearchService } from '../services/set-search.service';
import { CardSearchService } from '../services/card-search.service';
import { CardSet } from '../models/base-model';
import { Observable, defer, expand, map, mergeMap, of, take } from 'rxjs';

@Component({
  selector: 'app-search-box',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './search-box.component.html',
  styleUrl: './search-box.component.scss'
})
export class SearchBoxComponent {

  search_text: string = '';
  locked_prefix: string = '';
  locked_set?:CardSet;
  is_locked = false;

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
    // TODO: implement splicing the prefix of the search_text and putting it in locked_prefix. Need to implement service first
    if(this.is_locked) {
      
    }
  }

  private searchSetForLock(set$: Observable<CardSet>, max_iterations: number = this.max_set_size): Observable<CardSet> {
    return defer(() => {
      let word_len = this.min_set_size;
      let service_set$ = this.set_search.fetchSet(this.search_text.substring(0, word_len));

      return service_set$.pipe(
        expand((set_result) => {
          if(set_result || word_len >= max_iterations) {
            return of(set_result);
          }
          word_len++;

          return this.searchSetForLock(service_set$, max_iterations);
        }),
        take(max_iterations+1),
        map((result) => {
          if(!result) {
            console.error('NO SET FOUND!');
          }
          return result;
        })
      );
    });
  }
}
