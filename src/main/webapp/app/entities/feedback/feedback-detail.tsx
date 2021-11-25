import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './feedback.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const FeedbackDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const feedbackEntity = useAppSelector(state => state.feedback.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="feedbackDetailsHeading">
          <Translate contentKey="rfEduApp.feedback.detail.title">Feedback</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{feedbackEntity.id}</dd>
          <dt>
            <span id="rating">
              <Translate contentKey="rfEduApp.feedback.rating">Rating</Translate>
            </span>
          </dt>
          <dd>{feedbackEntity.rating}</dd>
          <dt>
            <span id="text">
              <Translate contentKey="rfEduApp.feedback.text">Text</Translate>
            </span>
          </dt>
          <dd>{feedbackEntity.text}</dd>
          <dt>
            <Translate contentKey="rfEduApp.feedback.event">Event</Translate>
          </dt>
          <dd>{feedbackEntity.event ? feedbackEntity.event.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/feedback" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/feedback/${feedbackEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default FeedbackDetail;
