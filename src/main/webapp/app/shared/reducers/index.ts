import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale from './locale';
import authentication from './authentication';
import applicationProfile from './application-profile';

import administration from 'app/modules/administration/administration.reducer';
import userManagement from 'app/modules/administration/user-management/user-management.reducer';
import register from 'app/modules/account/register/register.reducer';
import activate from 'app/modules/account/activate/activate.reducer';
import password from 'app/modules/account/password/password.reducer';
import settings from 'app/modules/account/settings/settings.reducer';
import passwordReset from 'app/modules/account/password-reset/password-reset.reducer';
import sessions from 'app/modules/account/sessions/sessions.reducer';
// prettier-ignore
import member from 'app/entities/member/member.reducer';
// prettier-ignore
import language from 'app/entities/language/language.reducer';
// prettier-ignore
import subject from 'app/entities/subject/subject.reducer';
// prettier-ignore
import type from 'app/entities/type/type.reducer';
// prettier-ignore
import event from 'app/entities/event/event.reducer';
// prettier-ignore
import mLog from 'app/entities/m-log/m-log.reducer';
// prettier-ignore
import attachment from 'app/entities/attachment/attachment.reducer';
// prettier-ignore
import schedule from 'app/entities/schedule/schedule.reducer';
// prettier-ignore
import feedback from 'app/entities/feedback/feedback.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const rootReducer = {
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  sessions,
  member,
  language,
  subject,
  type,
  event,
  mLog,
  attachment,
  schedule,
  feedback,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar,
};

export default rootReducer;
