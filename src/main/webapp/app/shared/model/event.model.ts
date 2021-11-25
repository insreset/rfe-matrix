import { ISchedule } from 'app/shared/model/schedule.model';
import { IFeedback } from 'app/shared/model/feedback.model';
import { IAttachment } from 'app/shared/model/attachment.model';
import { IMember } from 'app/shared/model/member.model';
import { ILanguage } from 'app/shared/model/language.model';
import { ISubject } from 'app/shared/model/subject.model';
import { IType } from 'app/shared/model/type.model';

export interface IEvent {
  id?: number;
  name?: string;
  detales?: string;
  jitsi?: string;
  schedules?: ISchedule[];
  feedbacks?: IFeedback[];
  attachments?: IAttachment[];
  organizer?: IMember;
  language?: ILanguage;
  subject?: ISubject;
  type?: IType;
}

export const defaultValue: Readonly<IEvent> = {};
