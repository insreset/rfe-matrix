import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './m-log.reducer';
import { IMLog } from 'app/shared/model/m-log.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const MLog = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const mLogList = useAppSelector(state => state.mLog.entities);
  const loading = useAppSelector(state => state.mLog.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="m-log-heading" data-cy="MLogHeading">
        <Translate contentKey="rfEduApp.mLog.home.title">M Logs</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="rfEduApp.mLog.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="rfEduApp.mLog.home.createLabel">Create new M Log</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {mLogList && mLogList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="rfEduApp.mLog.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="rfEduApp.mLog.rating">Rating</Translate>
                </th>
                <th>
                  <Translate contentKey="rfEduApp.mLog.event">Event</Translate>
                </th>
                <th>
                  <Translate contentKey="rfEduApp.mLog.member">Member</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {mLogList.map((mLog, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${mLog.id}`} color="link" size="sm">
                      {mLog.id}
                    </Button>
                  </td>
                  <td>{mLog.rating}</td>
                  <td>{mLog.event ? <Link to={`event/${mLog.event.id}`}>{mLog.event.id}</Link> : ''}</td>
                  <td>{mLog.member ? <Link to={`member/${mLog.member.id}`}>{mLog.member.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${mLog.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${mLog.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${mLog.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="rfEduApp.mLog.home.notFound">No M Logs found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default MLog;
