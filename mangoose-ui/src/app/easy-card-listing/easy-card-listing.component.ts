import { Component, ViewChild } from '@angular/core';
import { SearchBoxComponent } from '../search-box/search-box.component';
import { Card } from '../models/base-model';
import { TableResultsComponent } from '../table-results/table-results.component';

@Component({
  selector: 'app-easy-card-listing',
  standalone: true,
  imports: [SearchBoxComponent, TableResultsComponent],
  providers:[ TableResultsComponent ],
  templateUrl: './easy-card-listing.component.html',
  styleUrl: './easy-card-listing.component.scss'
})
export class EasyCardListingComponent {
  @ViewChild('app-table-results') tabResults:TableResultsComponent;
  
  constructor(
    public tableResultComp:TableResultsComponent
  ) {
    this.tabResults = tableResultComp;
  }

  handleCardResults(result:Card) {
    console.log(">>>> ", result);
    this.tabResults.addResult(result);
  }
}
