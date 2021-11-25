import dayjs from 'dayjs';
import { IEvent } from 'app/shared/model/event.model';

export interface ISchedule {
  id?: number;
  dateFrom?: string;
  dateTo?: string;
  event?: IEvent | null;
}

export const defaultValue: Readonly<ISchedule> = {};
