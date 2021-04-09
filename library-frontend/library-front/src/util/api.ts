import {environment} from '../environments/environment';

export class Api {
  static USER_END_POINT = environment.BASE_END_POINT;
  static BOOKS_END_POINT = environment.BASE_END_POINT + '/books';
  static RESERVATIONS_END_POINT = environment.BASE_END_POINT + '/reservations';
  // static BATTLESHIPS_END_POINT = environment.BASE_END_POINT + '/battleships';
}
