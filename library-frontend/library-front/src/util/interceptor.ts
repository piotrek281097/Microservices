import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable()
export class Interceptor implements HttpInterceptor {
  constructor() {}

  intercept(request: HttpRequest<any>, handler: HttpHandler): Observable<HttpEvent<any>> {
    const token = localStorage.getItem('token');

    if (!token) {
      return handler.handle(request);
    }

    const requestWithHeaders = request.clone({
      headers: request.headers.set('Authorization', `Bearer ${token}`).set('Content-Type', 'application/json'),
    });

    return handler.handle(requestWithHeaders);
  }

}
