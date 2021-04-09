import { Component, OnInit } from '@angular/core';
import {ReservationDto} from '../../models/reservationDto';
import {ReservationService} from '../../services/reservation.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-rental-service-user-reservations-list',
  templateUrl: './rental-service-user-reservations-list.component.html',
  styleUrls: ['./rental-service-user-reservations-list.component.css']
})
export class RentalServiceUserReservationsListComponent implements OnInit {

  reservations: ReservationDto[];
  username: string;

  constructor(private reservationService: ReservationService,
              private route: ActivatedRoute,
  ) { }

  ngOnInit() {
    this.username = this.route.snapshot.paramMap.get('username');

    this.reservationService.getUserRentalServiceReservationsForUser(this.username).subscribe(data => {
      this.reservations = data;
    });
  }

}
