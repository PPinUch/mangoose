import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: '', pathMatch: "full", redirectTo:'card-search'
    },
    {
        path: 'card-search',
        loadComponent: () => import('./easy-card-listing/easy-card-listing.component').then(ecl => ecl.EasyCardListingComponent)
    }
];
