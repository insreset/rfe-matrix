import { IEvent } from 'app/shared/model/event.model';
import { IMember } from 'app/shared/model/member.model';

export interface IMLog {
  id?: number;
  rating?: number;
  event?: IEvent;
  member?: IMember;
}

export const defaultValue: Readonly<IMLog> = {};
