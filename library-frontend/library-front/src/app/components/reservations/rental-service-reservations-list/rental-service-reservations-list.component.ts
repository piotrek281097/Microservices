import { Component, OnInit } from '@angular/core';
import {ReservationDto} from '../../../models/reservationDto';
import {ReservationService} from '../../../services/reservation.service';
import {MatTableDataSource} from '@angular/material';

@Component({
  selector: 'app-rental-service-reservations-list',
  templateUrl: './rental-service-reservations-list.component.html',
  styleUrls: ['./rental-service-reservations-list.component.css']
})
export class RentalServiceReservationsListComponent implements OnInit {

  reservations: ReservationDto[];
  public dataSource = new MatTableDataSource<ReservationDto>();

  constructor(private reservationService: ReservationService) { }

  ngOnInit() {
    this.reservationService.getUserRentalServiceReservations().subscribe(data => {
      if (data.length > 0) {
        this.dataSource.data = data;
      }
    });
  }

}
