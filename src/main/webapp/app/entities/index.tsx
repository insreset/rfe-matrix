import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Member from './member';
import Language from './language';
import Subject from './subject';
import Type from './type';
import Event from './event';
import MLog from './m-log';
import Attachment from './attachment';
import Schedule from './schedule';
import Feedback from './feedback';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}member`} component={Member} />
      <ErrorBoundaryRoute path={`${match.url}language`} component={Language} />
      <ErrorBoundaryRoute path={`${match.url}subject`} component={Subject} />
      <ErrorBoundaryRoute path={`${match.url}type`} component={Type} />
      <ErrorBoundaryRoute path={`${match.url}event`} component={Event} />
      <ErrorBoundaryRoute path={`${match.url}m-log`} component={MLog} />
      <ErrorBoundaryRoute path={`${match.url}attachment`} component={Attachment} />
      <ErrorBoundaryRoute path={`${match.url}schedule`} component={Schedule} />
      <ErrorBoundaryRoute path={`${match.url}feedback`} component={Feedback} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
