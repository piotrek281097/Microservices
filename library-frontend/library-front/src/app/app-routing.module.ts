import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import {AddBookComponent} from './components/books/add-book/add-book.component';
import {BooksListComponent} from './components/books/books-list/books-list.component';
import {MakeReservationComponent} from './components/reservations/make-reservation/make-reservation.component';
import {RegisterPageComponent} from './components/users/register-page/register-page.component';
import {LoginPageComponent} from './components/users/login-page/login-page.component';
import {ClassicLibraryMenuComponent} from './components/classic-library-menu/classic-library-menu.component';
import {UserRentalMenuComponent} from './components/user-rental-menu/user-rental-menu.component';
import {UsersListComponent} from './components/users/users-list/users-list.component';
import {UserDetailsComponent} from './components/users/user-details/user-details.component';
import {EditUserComponent} from './components/users/edit-user/edit-user.component';
import {ClassicLibraryBooksListComponent} from './components/books/classic-library-books-list/classic-library-books-list.component';
import {RentalServiceBooksListComponent} from './components/books/rental-service-books-list/rental-service-books-list.component';
import {ClassicLibraryReservationsListComponent} from './components/reservations/classic-library-reservations-list/classic-library-reservations-list.component';
import {RentalServiceReservationsListComponent} from './components/reservations/rental-service-reservations-list/rental-service-reservations-list.component';
import {ClassicLibraryUserReservationsListComponent} from './components/reservations/classic-library-user-reservations-list/classic-library-user-reservations-list.component';
import {RentalServiceUserReservationsListComponent} from './components/reservations/rental-service-user-reservations-list/rental-service-user-reservations-list.component';
import {RatingsComponent} from './components/books/ratings/ratings.component';
import {ReviewsListComponent} from './components/books/reviews-list/reviews-list.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'register', component: RegisterPageComponent },
  { path: 'login', component: LoginPageComponent },
  { path: 'classic-library-menu', component: ClassicLibraryMenuComponent },
  { path: 'user-rental-menu', component: UserRentalMenuComponent },
  { path: 'users-list', component: UsersListComponent },
  { path: 'user-details/:username', component: UserDetailsComponent },
  { path: 'edit-user/:username', component: EditUserComponent },
  { path: 'books/classic-library', component: ClassicLibraryBooksListComponent },
  { path: 'books/rental-service', component: RentalServiceBooksListComponent },
  { path: 'reservations/classic-library', component: ClassicLibraryReservationsListComponent },
  { path: 'reservations/rental-service', component: RentalServiceReservationsListComponent },
  { path: 'reservations/classic-library/:username', component: ClassicLibraryUserReservationsListComponent },
  { path: 'reservations/rental-service/:username', component: RentalServiceUserReservationsListComponent },
  { path: 'ratings', component: RatingsComponent },
  { path: 'reviews-list', component: ReviewsListComponent },
  { path: 'books', component: BooksListComponent },
  { path: 'add-book', component: AddBookComponent },
  { path: 'make-reservation/:bookId', component: MakeReservationComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
