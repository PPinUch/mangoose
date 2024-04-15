import { Component } from '@angular/core';
import { CardVO } from '../models/vo-model';
import { SetSearchService } from '../services/set-search.service';
import { Card } from '../models/base-model';

@Component({
  selector: 'app-table-results',
  standalone: true,
  imports: [],
  templateUrl: './table-results.component.html',
  styleUrl: './table-results.component.scss'
})
export class TableResultsComponent {

  result:CardVO[] = [];

  constructor(
    private set_service:SetSearchService
  ) {  }

  addResult(card:Card):void {
    if(!card || !card.set){
      return;
    }
    this.set_service.fetchSet(card.set).subscribe(set => {
      this.result.push({card, set});
    }
    )
  }
}
