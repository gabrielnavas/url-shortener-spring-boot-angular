import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {LinkResponse} from "./link-response";
import {LinkRequest} from "./link-request";

@Injectable({
  providedIn: 'root'
})
export class LinkService {

  constructor(
    private readonly httpClient: HttpClient
  ) {
  }

  createLink(params: { body: LinkRequest }): Observable<LinkResponse> {
    return this.httpClient.post<LinkResponse>('http://localhost:8080/api/v1/url-shortener', params.body)
  }
}
