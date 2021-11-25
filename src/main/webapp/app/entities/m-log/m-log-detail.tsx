import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './m-log.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const MLogDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const mLogEntity = useAppSelector(state => state.mLog.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="mLogDetailsHeading">
          <Translate contentKey="rfEduApp.mLog.detail.title">MLog</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{mLogEntity.id}</dd>
          <dt>
            <span id="rating">
              <Translate contentKey="rfEduApp.mLog.rating">Rating</Translate>
            </span>
          </dt>
          <dd>{mLogEntity.rating}</dd>
          <dt>
            <Translate contentKey="rfEduApp.mLog.event">Event</Translate>
          </dt>
          <dd>{mLogEntity.event ? mLogEntity.event.id : ''}</dd>
          <dt>
            <Translate contentKey="rfEduApp.mLog.member">Member</Translate>
          </dt>
          <dd>{mLogEntity.member ? mLogEntity.member.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/m-log" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/m-log/${mLogEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default MLogDetail;
