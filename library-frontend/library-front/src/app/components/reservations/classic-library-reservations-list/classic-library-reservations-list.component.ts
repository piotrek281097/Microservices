import { Component, OnInit } from '@angular/core';
import {ReservationDto} from '../../../models/reservationDto';
import {ReservationService} from '../../../services/reservation.service';
import {MatTableDataSource} from '@angular/material';

@Component({
  selector: 'app-classic-library-reservations-list',
  templateUrl: './classic-library-reservations-list.component.html',
  styleUrls: ['./classic-library-reservations-list.component.css']
})
export class ClassicLibraryReservationsListComponent implements OnInit {

  public dataSource = new MatTableDataSource<ReservationDto>();

  constructor(private reservationService: ReservationService) { }

  ngOnInit() {
    this.reservationService.getClassicLibraryReservations().subscribe(data => {
      if (data.length > 0) {
        this.dataSource.data = data;
      }
    });
  }

}
