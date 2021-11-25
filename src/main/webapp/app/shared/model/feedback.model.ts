import { IEvent } from 'app/shared/model/event.model';

export interface IFeedback {
  id?: number;
  rating?: number;
  text?: string;
  event?: IEvent | null;
}

export const defaultValue: Readonly<IFeedback> = {};
