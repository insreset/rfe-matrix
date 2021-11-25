import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './schedule.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const ScheduleDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const scheduleEntity = useAppSelector(state => state.schedule.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="scheduleDetailsHeading">
          <Translate contentKey="rfEduApp.schedule.detail.title">Schedule</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{scheduleEntity.id}</dd>
          <dt>
            <span id="dateFrom">
              <Translate contentKey="rfEduApp.schedule.dateFrom">Date From</Translate>
            </span>
          </dt>
          <dd>{scheduleEntity.dateFrom ? <TextFormat value={scheduleEntity.dateFrom} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="dateTo">
              <Translate contentKey="rfEduApp.schedule.dateTo">Date To</Translate>
            </span>
          </dt>
          <dd>{scheduleEntity.dateTo ? <TextFormat value={scheduleEntity.dateTo} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <Translate contentKey="rfEduApp.schedule.event">Event</Translate>
          </dt>
          <dd>{scheduleEntity.event ? scheduleEntity.event.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/schedule" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/schedule/${scheduleEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ScheduleDetail;
