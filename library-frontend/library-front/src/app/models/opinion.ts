import {Book} from './book';

export class Opinion {
  id: number;
  grade: number;
  review: string;
  book: Book;

  constructor() {
  }
}
