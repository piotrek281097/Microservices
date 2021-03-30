import {Reader} from './reader';

export class PageableReaderResponse {
  readers: Reader[];
  totalPages: number;
  pageNumber: number;
  pageSize: number;
}
