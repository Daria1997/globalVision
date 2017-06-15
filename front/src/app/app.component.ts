import {Component, OnInit} from '@angular/core';
import {Project} from "./models/project.model";
import {AppService} from "./services/app.service";
import {Perfomer} from "./models/performer.model";
import {Manager} from "./models/manager.model";
import {Task} from "./models/task.model";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  public projects: Project[];
  public selectedProject: Project;
  public performers: Perfomer[];
  public manager: Manager;
  public tasks: Task[];

  constructor(private _appService: AppService) {}

  public async ngOnInit() {
    this.projects = await this._appService.getProjects();
    this.tasks = await this._appService.getTasks();
  }

  public async showProjectInfo(p: Project): Promise<void> {
    this.selectedProject = await this._appService.getProject(p.id);
    this.performers = await this._appService.getPerformers();
    this.manager = await this._appService.getManager(p.manager_id);
  }

}
