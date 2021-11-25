import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './member.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const MemberDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const memberEntity = useAppSelector(state => state.member.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="memberDetailsHeading">
          <Translate contentKey="rfEduApp.member.detail.title">Member</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{memberEntity.id}</dd>
          <dt>
            <span id="picture">
              <Translate contentKey="rfEduApp.member.picture">Picture</Translate>
            </span>
          </dt>
          <dd>{memberEntity.picture}</dd>
          <dt>
            <span id="realname">
              <Translate contentKey="rfEduApp.member.realname">Realname</Translate>
            </span>
          </dt>
          <dd>{memberEntity.realname}</dd>
          <dt>
            <span id="username">
              <Translate contentKey="rfEduApp.member.username">Username</Translate>
            </span>
          </dt>
          <dd>{memberEntity.username}</dd>
          <dt>
            <span id="password">
              <Translate contentKey="rfEduApp.member.password">Password</Translate>
            </span>
          </dt>
          <dd>{memberEntity.password}</dd>
          <dt>
            <span id="address">
              <Translate contentKey="rfEduApp.member.address">Address</Translate>
            </span>
          </dt>
          <dd>{memberEntity.address}</dd>
          <dt>
            <span id="matrix">
              <Translate contentKey="rfEduApp.member.matrix">Matrix</Translate>
            </span>
          </dt>
          <dd>{memberEntity.matrix}</dd>
        </dl>
        <Button tag={Link} to="/member" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/member/${memberEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default MemberDetail;
