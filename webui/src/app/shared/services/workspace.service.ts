import { Injectable } from '@angular/core';
import { Observable} from 'rxjs';
import { ApiRequestService } from './api-request.service';
import { Workspace } from "app/shared";

@Injectable()
export class WorkspaceService {
    constructor(
        private apiRequest: ApiRequestService
    ) {}

    getWorkspaces(): Observable<any> {
        return this.apiRequest.get('api/workspaces/findAll');
    }

    getWorkgroups(): Observable<any> {
        return this.apiRequest.get('api/workspaces/getWorkgroups');
    }

    createWorkspace(workspace:Workspace): Observable<any> {
        return this.apiRequest.post('api/workspaces/create', workspace);
    }

    updateWorkspace(workspace:Workspace): Observable<any> {
        return this.apiRequest.post('api/workspaces/update', workspace);
    }

    deleteWorkspace(workspace:Workspace): Observable<any> {
        return this.apiRequest.post('api/workspaces/delete', workspace);
    }
}
