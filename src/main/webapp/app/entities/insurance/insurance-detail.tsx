import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './insurance.reducer';

export const InsuranceDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const insuranceEntity = useAppSelector(state => state.insurance.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="insuranceDetailsHeading">
          <Translate contentKey="wayThereJhipsterApp.insurance.detail.title">Insurance</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{insuranceEntity.id}</dd>
          <dt>
            <span id="insID">
              <Translate contentKey="wayThereJhipsterApp.insurance.insID">Ins ID</Translate>
            </span>
          </dt>
          <dd>{insuranceEntity.insID}</dd>
          <dt>
            <span id="insDescription">
              <Translate contentKey="wayThereJhipsterApp.insurance.insDescription">Ins Description</Translate>
            </span>
          </dt>
          <dd>{insuranceEntity.insDescription}</dd>
          <dt>
            <span id="startDate">
              <Translate contentKey="wayThereJhipsterApp.insurance.startDate">Start Date</Translate>
            </span>
          </dt>
          <dd>
            {insuranceEntity.startDate ? <TextFormat value={insuranceEntity.startDate} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="expirationDate">
              <Translate contentKey="wayThereJhipsterApp.insurance.expirationDate">Expiration Date</Translate>
            </span>
          </dt>
          <dd>
            {insuranceEntity.expirationDate ? (
              <TextFormat value={insuranceEntity.expirationDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/insurance" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/insurance/${insuranceEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default InsuranceDetail;
