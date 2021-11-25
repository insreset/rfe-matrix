import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './type.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const TypeDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const typeEntity = useAppSelector(state => state.type.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="typeDetailsHeading">
          <Translate contentKey="rfEduApp.type.detail.title">Type</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{typeEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="rfEduApp.type.name">Name</Translate>
            </span>
          </dt>
          <dd>{typeEntity.name}</dd>
          <dt>
            <span id="picture">
              <Translate contentKey="rfEduApp.type.picture">Picture</Translate>
            </span>
          </dt>
          <dd>{typeEntity.picture}</dd>
        </dl>
        <Button tag={Link} to="/type" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/type/${typeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default TypeDetail;
