import { Injectable } from '@angular/core';
import {Http} from "@angular/http";
import {Project} from "../models/project.model";
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/toPromise'

@Injectable()
export class AppService {
  private API_URL = 'http://localhost:8080/rest/';

  constructor(private http: Http) { }

  public getProjects(): Promise<Project[]> {
    return this.http.get(this.API_URL + 'projects').map((list) => {
      return list.json();
    }).toPromise();
  }

}
