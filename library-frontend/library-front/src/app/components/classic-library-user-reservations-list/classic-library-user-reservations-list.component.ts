import { Component, OnInit } from '@angular/core';
import {ReservationDto} from '../../models/reservationDto';
import {ReservationService} from '../../services/reservation.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-classic-library-user-reservations-list',
  templateUrl: './classic-library-user-reservations-list.component.html',
  styleUrls: ['./classic-library-user-reservations-list.component.css']
})
export class ClassicLibraryUserReservationsListComponent implements OnInit {

  reservations: ReservationDto[];
  username: string;

  constructor(private reservationService: ReservationService,
              private route: ActivatedRoute,
  ) { }

  ngOnInit() {
    this.username = this.route.snapshot.paramMap.get('username');

    this.reservationService.getClassicLibraryReservationsForUser(this.username).subscribe(data => {
      this.reservations = data;
    });
  }

}
