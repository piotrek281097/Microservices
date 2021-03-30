import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
@Injectable()
export class BasicAuthInterceptor implements HttpInterceptor {
  private login = 'admin1';
  private password = 'password1';
  constructor() {
  }
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    request = request.clone({
      setHeaders: {
        Authorization: 'Basic ' + btoa(this.login + ':' + this.password),
        AccessControlAllowMethods: 'POST, GET, OPTIONS, PUT, DELETE',
        AccessControlAllowOrigin: 'https://bankrestapicomarch.herokuapp.com/'
      }
    });

    return next.handle(request);
  }
}
