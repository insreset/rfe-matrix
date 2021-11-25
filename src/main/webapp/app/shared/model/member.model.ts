export interface IMember {
  id?: number;
  picture?: string;
  realname?: string;
  username?: string;
  password?: string;
  address?: string;
  matrix?: string;
}

export const defaultValue: Readonly<IMember> = {};
