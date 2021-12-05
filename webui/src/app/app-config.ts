import { Injectable } from '@angular/core';

/**
 * This is a singleton class
 */
@Injectable()
export class AppConfig {
    //Provide all the Application Configs here

    public version:string = "1.0.0";
    public locale:string  = "en-US";

    // API Related configs
    public apiPort:string; // change port if backend works on a different port than frontend
    public apiProtocol:string;
    public apiHostName:string;
    public baseApiPath:string;

    constructor(){
        if (this.apiProtocol===undefined){
            this.apiProtocol = window.location.protocol;
        }
        if (this.apiHostName===undefined){
            this.apiHostName = window.location.hostname;
        }
        if (this.apiPort===undefined){
            this.apiPort = window.location.port;
        }

        this.baseApiPath = this.apiProtocol + "//" + this.apiHostName;

        if(this.apiPort!==undefined && this.apiPort!==null && this.apiPort!=="") {
            this.baseApiPath = this.baseApiPath + ":" + this.apiPort;
        }
        this.baseApiPath = this.baseApiPath +"/";

        if (this.locale===undefined){
            this.locale = navigator.language;
        }
    }
}
