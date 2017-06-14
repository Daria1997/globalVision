import {Component, OnInit} from '@angular/core';
import {Project} from "./models/project.model";
import {AppService} from "./services/app.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  public projects: Project[];

  constructor(private _appService: AppService) {}

  public async ngOnInit() {
    this.projects = await this._appService.getProjects();
  }
}
