import { Component } from '@angular/core';
import { CardVO } from '../models/vo-model';
import { SetSearchService } from '../services/set-search.service';
import { Card } from '../models/base-model';
import { TableModule } from 'primeng/table';

@Component({
  selector: 'app-table-results',
  standalone: true,
  imports: [TableModule],
  templateUrl: './table-results.component.html',
  styleUrl: './table-results.component.scss'
})
export class TableResultsComponent {

  result:CardVO[] = [];
  cnt = 0;

  constructor(
    private set_service:SetSearchService
  ) {  }

  addResult(card:Card):void {
    console.log('!PING!')
    if(!card || !card.set){
      return;
    }
    this.set_service.fetchSet(card.set).subscribe(set => {
      let found = false;
      this.cnt += 1;
      this.result.forEach((item) => {
        if(card.oracleId = item.card?.oracleId) {
          item.qnt += 1;
          found = true;
          return;
        }
      });
      if(!found) {
        this.result.push({set, card, qnt:1});
      }
    });
  }
}
