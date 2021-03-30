import {Reservation} from './reservation';
import {Fine} from './fine';

export class Reader {
  id: number;
  name: string;
  surname: string;
  telephone: string;
  pesel: string;
  reservations: Reservation[];
  fines: Fine[];

  constructor() {
  }
}
