import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse,
} from '@angular/common/http';
import { Observable, switchMap, catchError, throwError } from 'rxjs';
import { AuthService } from './auth.service';
import { environment } from '../../environments/environment.development';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  constructor(private authSvc: AuthService) {}

  intercept(
    request: HttpRequest<unknown>,
    next: HttpHandler
  ): Observable<HttpEvent<unknown>> {
    // Escludi la richiesta di login dall'intercettazione
    if (request.url.includes(environment.loginUrl)) {
      return next.handle(request);
    }

    const accessData = this.authSvc.authSubject$.getValue();
    if (!accessData) {
      return next.handle(request);
    }

    const newRequest = request.clone({
      headers: request.headers.append(
        'Authorization',
        `Bearer ${accessData.accessToken}`
      ),
    });

    return next.handle(newRequest).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401) {
          // Gestisci l'errore di autenticazione
          this.authSvc.logout();
          alert('Sessione scaduta. Effettua nuovamente il login.');
        }
        return throwError(error);
      })
    );
  }
}
