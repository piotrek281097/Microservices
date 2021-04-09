import {Opinion} from './opinion';

export class Book {
  id: number;
  title: string;
  author: string;
  identifier: string;
  bookKind: string;
  bookStatus: string;
  releaseDate: string;
  ownerUsername: string;
  avgRate: number;
  opinions: Opinion[];

  constructor() {
  }

}
