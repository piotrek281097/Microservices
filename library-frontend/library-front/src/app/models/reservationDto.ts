import {Reader} from './reader';
import {Book} from './book';

export class ReservationDto {
  id: number;
  startDate: string;
  endDate: string;
  reservationStatus: string;
  reader: Reader;
  book: Book;

  constructor() {
  }
}
