import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

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
  is_locked = false;

  constructor() {}

  toggleLockComponent(): void {
    if(this.search_text === '' && !this.is_locked) {
      return;
    }

    this.is_locked = !this.is_locked;
    // TODO: implement splicing the prefix of the search_text and putting it in locked_prefix. Need to implement service first
  }
}
