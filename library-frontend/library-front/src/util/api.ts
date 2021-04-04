import {environment} from '../environments/environment';

export class Api {
  static USER_END_POINT = environment.BASE_END_POINT;
  static BOOKS_END_POINT = environment.BASE_END_POINT + '/books';
  // static REVERSI_END_POINT = environment.BASE_END_POINT + '/reversi';
  // static BATTLESHIPS_END_POINT = environment.BASE_END_POINT + '/battleships';
}
