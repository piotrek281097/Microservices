import { Component, OnInit } from '@angular/core';
import {ReservationDto} from '../../models/reservationDto';
import {ReservationService} from '../../services/reservation.service';

@Component({
  selector: 'app-classic-library-reservations-list',
  templateUrl: './classic-library-reservations-list.component.html',
  styleUrls: ['./classic-library-reservations-list.component.css']
})
export class ClassicLibraryReservationsListComponent implements OnInit {
  reservations: ReservationDto[];

  constructor(private reservationService: ReservationService) { }

  ngOnInit() {
    this.reservationService.getClassicLibraryReservations().subscribe(data => {
      this.reservations = data;
    });
  }

}
