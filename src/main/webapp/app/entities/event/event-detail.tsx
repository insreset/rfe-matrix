import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './event.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const EventDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const eventEntity = useAppSelector(state => state.event.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="eventDetailsHeading">
          <Translate contentKey="rfEduApp.event.detail.title">Event</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{eventEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="rfEduApp.event.name">Name</Translate>
            </span>
          </dt>
          <dd>{eventEntity.name}</dd>
          <dt>
            <span id="detales">
              <Translate contentKey="rfEduApp.event.detales">Detales</Translate>
            </span>
          </dt>
          <dd>{eventEntity.detales}</dd>
          <dt>
            <span id="jitsi">
              <Translate contentKey="rfEduApp.event.jitsi">Jitsi</Translate>
            </span>
          </dt>
          <dd>{eventEntity.jitsi}</dd>
          <dt>
            <Translate contentKey="rfEduApp.event.organizer">Organizer</Translate>
          </dt>
          <dd>{eventEntity.organizer ? eventEntity.organizer.id : ''}</dd>
          <dt>
            <Translate contentKey="rfEduApp.event.language">Language</Translate>
          </dt>
          <dd>{eventEntity.language ? eventEntity.language.id : ''}</dd>
          <dt>
            <Translate contentKey="rfEduApp.event.subject">Subject</Translate>
          </dt>
          <dd>{eventEntity.subject ? eventEntity.subject.id : ''}</dd>
          <dt>
            <Translate contentKey="rfEduApp.event.type">Type</Translate>
          </dt>
          <dd>{eventEntity.type ? eventEntity.type.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/event" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/event/${eventEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default EventDetail;
