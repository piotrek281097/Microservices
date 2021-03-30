import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import {AddBookComponent} from './components/add-book/add-book.component';
import {BooksListComponent} from './components/books-list/books-list.component';
import {AddReaderComponent} from './components/add-reader/add-reader.component';
import {EditReaderComponent} from './components/edit-reader/edit-reader.component';
import {ReaderDetailsComponent} from './components/reader-details/reader-details.component';
import {MakeReservationComponent} from './components/make-reservation/make-reservation.component';
import {AddFineComponent} from './components/add-fine/add-fine.component';
import {RegisterPageComponent} from './components/register-page/register-page.component';
import {LoginPageComponent} from './components/login-page/login-page.component';
import {MenuComponent} from './components/menu/menu.component';
import {ClassicLibraryMenuComponent} from './components/classic-library-menu/classic-library-menu.component';
import {UserRentalMenuComponent} from './components/user-rental-menu/user-rental-menu.component';
import {ReadersListComponent} from './components/readers-list/readers-list.component';
import {UsersListComponent} from './components/users-list/users-list.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'register', component: RegisterPageComponent },
  { path: 'login', component: LoginPageComponent },
  { path: 'menu', component: MenuComponent },
  { path: 'classic-library-menu', component: ClassicLibraryMenuComponent },
  { path: 'user-rental-menu', component: UserRentalMenuComponent },
  { path: 'users-list', component: UsersListComponent },
  { path: 'books', component: BooksListComponent },
  { path: 'add-reader', component: AddReaderComponent },
  { path: 'add-book', component: AddBookComponent },
  { path: 'add-fine', component: AddFineComponent },
  { path: 'edit-reader/:readerId', component: EditReaderComponent },
  { path: 'reader-details/:readerId', component: ReaderDetailsComponent },
  { path: 'make-reservation/:bookId', component: MakeReservationComponent },
  { path: 'readers-list', component: ReadersListComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
