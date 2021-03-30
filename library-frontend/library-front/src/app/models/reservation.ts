import {Book} from './book';
import {Reader} from './reader';

export class Reservation {
  id: number;
  startDate: string;
  endDate: string;
  reservationStatus: string;
  book: Book;
  reader: Reader;

  constructor() {}
}
