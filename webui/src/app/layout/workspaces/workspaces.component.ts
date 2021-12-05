import { Component, OnInit } from '@angular/core';
import { WorkspaceService } from 'app/shared';
import { Workspace } from 'app/shared';
import { SelectItem } from "primeng/components/common/selectitem";
import { TranslateService } from "@ngx-translate/core";
import { Validators,FormControl,FormGroup,FormBuilder} from '@angular/forms';
import { BasePageComponent } from "app/layout/base.page.component";
import { PrimeMessageType } from "app/shared";
import { OperationResult} from "app/shared";

@Component({
    templateUrl: 'workspaces.component.html'
})
export class WorkspacesComponent extends BasePageComponent implements OnInit {

    constructor(private workspaceService: WorkspaceService, translateService: TranslateService, private formBuilder: FormBuilder) {
        super(translateService);
    }

    displayDialog: boolean;

    workspace: Workspace = {};

    selectedWorkspace: Workspace;

    newWorkspace: boolean;

    workspaces: Workspace[];

    workGroups: SelectItem[];

    workspaceForm: FormGroup;

    ngOnInit() {
        this.workspaceForm = this.formBuilder.group({
            'workGroup': new FormControl('', Validators.required),
            'workspaceName': new FormControl('', Validators.required),
            'workspaceUrl': new FormControl('', Validators.pattern('http://.*'))
        });
        this.workspaceService.getWorkspaces().subscribe(workspaces => this.workspaces = workspaces);
    }

    save() {
        let workspaces = [...this.workspaces];
        if (this.newWorkspace) {
            this.workspaceService.createWorkspace(this.workspace).subscribe(
                operationResponse => {
                    if(operationResponse.operationStatus === OperationResult.SUCCESS) {
                        workspaces.push(operationResponse.responseObject);
                        this.pushMessage(PrimeMessageType.success, '', operationResponse.operationMessage);
                    } else {
                        this.pushMessage(PrimeMessageType.error, '', operationResponse.operationMessage);
                    }
                });
        } else {
            this.workspaceService.updateWorkspace(this.workspace).subscribe(
                operationResponse => {
                    if(operationResponse.operationStatus === OperationResult.SUCCESS) {
                        workspaces[this.workspaces.indexOf(this.selectedWorkspace)] = operationResponse.responseObject;
                        this.pushMessage(PrimeMessageType.success, '', operationResponse.operationMessage);
                    } else {
                        this.pushMessage(PrimeMessageType.error, '', operationResponse.operationMessage);
                    }
                });
        }

        this.workspaces = workspaces;
        this.workspace =  {};
        this.displayDialog = false;
    }

    delete() {
        let index = this.workspaces.indexOf(this.selectedWorkspace);
        this.workspaceService.deleteWorkspace(this.workspace).subscribe(
            operationResponse => {
                if(operationResponse.operationStatus === OperationResult.SUCCESS) {
                    this.workspaces = this.workspaces.filter((val, i) => i != index);
                    this.workspace =  {};
                    this.displayDialog = false;
                    this.pushMessage(PrimeMessageType.success, '', operationResponse.operationMessage);
                } else {
                    this.pushMessage(PrimeMessageType.error, '', operationResponse.operationMessage);
                }
            });
    }

    showDialogToAdd() {
        this.newWorkspace = true;
        this.workspace = {};
        this.showCrudDialog();
    }

    onRowSelect(event) {
        this.newWorkspace = false;
        this.workspace = this.cloneWorkspace(event.data);
        this.showCrudDialog();
    }

    showCrudDialog(){
        this.workspaceForm.reset();
        this.workspaceService.getWorkgroups().subscribe(response => {
            this.workGroups = [];
            for (let workGroup of response) {
                let item = {label:workGroup, name:workGroup, value:workGroup};
                this.workGroups.push(item);
            }
            this.displayDialog = true;
        });
    }

    cloneWorkspace(w: Workspace): Workspace {
        let workspace = {};
        for (let prop in w) {
            workspace[prop] = w[prop];
        }
        return workspace;
    }

}
