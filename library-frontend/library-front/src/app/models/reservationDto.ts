export class ReservationDto {
  id: number;
  reservationIdentifier: string;
  startDate: string;
  endDate: string;
  reservationStatus: string;
  bookTitle: string;
  bookIdentifier: string;
  borrowerUsername: string;
  ownerUsername: string;

  constructor() {
  }
}
