import { Injectable } from '@angular/core';
import {Http} from "@angular/http";
import {Project} from "../models/project.model";
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/toPromise'
import {Manager} from "../models/manager.model";
import {Perfomer} from "../models/performer.model";
import {Task} from "../models/task.model";

@Injectable()
export class AppService {
  private API_URL = 'http://localhost:8080/rest/';

  constructor(private http: Http) { }

  public getProjects(): Promise<Project[]> {
    return this.http.get(this.API_URL + 'projects').map((list) => {
      return list.json();
    }).toPromise();
  }

  public getTasks(): Promise<Task[]> {
    return this.http.get(this.API_URL + 'tasks').map((list) => {
      return list.json();
    }).toPromise();
  }

  public getPerformers(): Promise<Perfomer[]> {
    return this.http.get(this.API_URL + 'performers').map((list) => {
      return list.json();
    }).toPromise();
  }

  public getManager(id: number): Promise<Manager> {
    return this.http.get(this.API_URL + 'manager?id=1').map((m) => {
      return m.json();
    }).toPromise();
  }

  public getProject(id): Promise<Project> {
    return this.http.get(this.API_URL + 'project?id='+id).map((pr) => {
      return pr.json();
    }).toPromise();
  }

}
