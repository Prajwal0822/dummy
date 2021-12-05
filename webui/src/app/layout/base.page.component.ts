import {Message} from "primeng/components/common/message";
import {PrimeMessageType} from "app/shared";
import {TranslateService} from "@ngx-translate/core";

export class BasePageComponent {

    msgs: Message[] = [];

    constructor(private translateService: TranslateService) { }

    pushMessage(messageType:PrimeMessageType, summaryMessage:string, detailMessage:string){
        let primeMessageType:string = PrimeMessageType[messageType];
        let translatedSummary = (summaryMessage !== '') ? this.translateService.instant(summaryMessage).toString() : summaryMessage;
        let translatedDetail = (detailMessage !== '') ? this.translateService.instant(detailMessage).toString() : detailMessage;
        this.msgs = [];
        this.msgs.push({severity:primeMessageType, summary:translatedSummary, detail:translatedDetail});
    }
}