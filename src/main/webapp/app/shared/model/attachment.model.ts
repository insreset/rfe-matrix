import { IEvent } from 'app/shared/model/event.model';

export interface IAttachment {
  id?: number;
  title?: string;
  link?: string;
  event?: IEvent | null;
}

export const defaultValue: Readonly<IAttachment> = {};
