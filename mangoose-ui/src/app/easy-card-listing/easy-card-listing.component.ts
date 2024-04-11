import { Component } from '@angular/core';
import { SearchBoxComponent } from '../search-box/search-box.component';

@Component({
  selector: 'app-easy-card-listing',
  standalone: true,
  imports: [SearchBoxComponent],
  templateUrl: './easy-card-listing.component.html',
  styleUrl: './easy-card-listing.component.scss'
})
export class EasyCardListingComponent {

}
