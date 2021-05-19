import { BrowserModule } from '@angular/platform-browser';
import { NgModule , CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavComponent } from './components/nav/nav.component';
import { HomeComponent } from './components/home/home.component';
import {MatNativeDateModule} from '@angular/material/core';

import {
  MatMenuModule,
  MatIconModule,
  MatTableModule,
  MatGridListModule,
  MatRadioModule,
  MatPaginatorModule,
  MatSortModule
} from '@angular/material';
import { BrowserAnimationsModule, NoopAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material/input';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatCardModule } from '@angular/material/card';
import { ToastrModule } from 'ngx-toastr';
import { NgHttpLoaderModule } from 'ng-http-loader';
import { MatDialogModule } from '@angular/material/dialog';
import { FilterPipeModule } from 'ngx-filter-pipe';
import { MatTabsModule } from '@angular/material';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { NgxPaginationModule } from 'ngx-pagination';
import { AddBookComponent } from './components/books/add-book/add-book.component';
import { BooksListComponent } from './components/books/books-list/books-list.component';
import { MakeReservationComponent } from './components/reservations/make-reservation/make-reservation.component';
import { RegisterPageComponent } from './components/users/register-page/register-page.component';
import { LoginPageComponent } from './components/users/login-page/login-page.component';
import { ClassicLibraryMenuComponent } from './components/classic-library-menu/classic-library-menu.component';
import { UserRentalMenuComponent } from './components/user-rental-menu/user-rental-menu.component';
import { UsersListComponent } from './components/users/users-list/users-list.component';
import {Interceptor} from '../util/interceptor';
import { UserDetailsComponent } from './components/users/user-details/user-details.component';
import { EditUserComponent } from './components/users/edit-user/edit-user.component';
import { ClassicLibraryBooksListComponent } from './components/books/classic-library-books-list/classic-library-books-list.component';
import { RentalServiceBooksListComponent } from './components/books/rental-service-books-list/rental-service-books-list.component';
import { ClassicLibraryReservationsListComponent } from './components/reservations/classic-library-reservations-list/classic-library-reservations-list.component';
import { RentalServiceReservationsListComponent } from './components/reservations/rental-service-reservations-list/rental-service-reservations-list.component';
import { ReservationsListComponent } from './components/reservations/reservations-list/reservations-list.component';
import { ClassicLibraryUserReservationsListComponent } from './components/reservations/classic-library-user-reservations-list/classic-library-user-reservations-list.component';
import { RentalServiceUserReservationsListComponent } from './components/reservations/rental-service-user-reservations-list/rental-service-user-reservations-list.component';
import { RateBookComponent } from './components/books/rate-book/rate-book.component';
import {NgbRatingModule} from '@ng-bootstrap/ng-bootstrap';
import { RatingsComponent } from './components/books/ratings/ratings.component';
import { ReviewsListComponent } from './components/books/reviews-list/reviews-list.component';
import { ConfirmDeleteUserComponent } from './components/confirm-delete-user/confirm-delete-user.component';
import { ConfirmDeleteBookComponent } from './components/confirm-delete-book/confirm-delete-book.component';



@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    HomeComponent,
    AddBookComponent,
    BooksListComponent,
    MakeReservationComponent,
    RegisterPageComponent,
    LoginPageComponent,
    ClassicLibraryMenuComponent,
    UserRentalMenuComponent,
    UsersListComponent,
    UserDetailsComponent,
    EditUserComponent,
    ClassicLibraryBooksListComponent,
    RentalServiceBooksListComponent,
    ClassicLibraryReservationsListComponent,
    RentalServiceReservationsListComponent,
    ReservationsListComponent,
    ClassicLibraryUserReservationsListComponent,
    RentalServiceUserReservationsListComponent,
    RateBookComponent,
    RatingsComponent,
    ReviewsListComponent,
    ConfirmDeleteUserComponent,
    ConfirmDeleteBookComponent,
  ],
  imports: [
    ReactiveFormsModule.withConfig({warnOnNgModelWithFormControl: 'never'}),
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    MatMenuModule,
    MatIconModule,
    MatToolbarModule,
    MatDialogModule,
    MatButtonModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatCardModule,
    MatTabsModule,
    MatNativeDateModule,
    NoopAnimationsModule,
    BrowserAnimationsModule,
    FilterPipeModule,
    MatAutocompleteModule,
    NgxPaginationModule,
    NgHttpLoaderModule.forRoot(),
    ToastrModule.forRoot({
      timeOut: 3000,
      progressBar: true,
      extendedTimeOut: 2000,
      easeTime: 500,
      resetTimeoutOnDuplicate: true
    }),
    MatGridListModule,
    MatRadioModule,
    NgbRatingModule,
    MatPaginatorModule,
    MatSortModule,
  ],
  entryComponents: [RateBookComponent, ConfirmDeleteUserComponent, ConfirmDeleteBookComponent],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: Interceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
