import {Book} from './book';

export class PageableBookResponse {
  books: Book[];
  totalPages: number;
  pageNumber: number;
  pageSize: number;
}
